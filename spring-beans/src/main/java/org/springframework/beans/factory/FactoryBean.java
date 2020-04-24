/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory;

import org.springframework.lang.Nullable;

/**1.这个接口一般被用在BeanFactory中, 让其它对象实现
 * 2.如果某个bean实现了这个接口, 它通常作为bean工厂来暴露其它bean, 一般不会用来暴露自己
 * 3.实现了FactoryBean接口的bean不能用作普通bean, 虽然它被定义成bean的方式,但是getObject()返回的总是它创建的bean对象
 * 4.FactoryBean支持singleton和prototype模式, 既可以懒加载bean也可以在beanFactory启动时立即创建bean对象
 * 5.FactoryBean在框架中被大量使用, 如ProxyFactoryBean和JndiObjectFactoryBean, 也能被自定义的组件使用, 但是, FactoryBean通常只被底层代码使用
 * 6.FactoryBean一般是基于编程式的, 不基于注解和依赖注入, 有可能在beanFactory启动之前就调用getObjectType()和getObject()方法, 甚至在后置处理器触发之前, 如果需求获取其它的bean, 可以实现BeanFactoryAware接口, 然后手动获取
 * 7.FactoryBean在beanFactory中是同步创建的, 内部不需要同步机制, 除非在内部需要懒加载
 *
 * Interface to be implemented by objects used within a {@link BeanFactory} which
 * are themselves factories for individual objects. If a bean implements this
 * interface, it is used as a factory for an object to expose, not directly as a
 * bean instance that will be exposed itself.
 *
 * <p><b>NB: A bean that implements this interface cannot be used as a normal bean.</b>
 * A FactoryBean is defined in a bean style, but the object exposed for bean
 * references ({@link #getObject()}) is always the object that it creates.
 *
 * <p>FactoryBeans can support singletons and prototypes, and can either create
 * objects lazily on demand or eagerly on startup. The {@link SmartFactoryBean}
 * interface allows for exposing more fine-grained behavioral metadata.
 *
 * <p>This interface is heavily used within the framework itself, for example for
 * the AOP {@link org.springframework.aop.framework.ProxyFactoryBean} or the
 * {@link org.springframework.jndi.JndiObjectFactoryBean}. It can be used for
 * custom components as well; however, this is only common for infrastructure code.
 *
 * <p><b>{@code FactoryBean} is a programmatic contract. Implementations are not
 * supposed to rely on annotation-driven injection or other reflective facilities.</b>
 * {@link #getObjectType()} {@link #getObject()} invocations may arrive early in
 * the bootstrap process, even ahead of any post-processor setup. If you need access
 * other beans, implement {@link BeanFactoryAware} and obtain them programmatically.
 *
 * <p>Finally, FactoryBean objects participate in the containing BeanFactory's
 * synchronization of bean creation. There is usually no need for internal
 * synchronization other than for purposes of lazy initialization within the
 * FactoryBean itself (or the like).
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 08.03.2003
 * @param <T> the bean type
 * @see org.springframework.beans.factory.BeanFactory
 * @see org.springframework.aop.framework.ProxyFactoryBean
 * @see org.springframework.jndi.JndiObjectFactoryBean
 */
public interface FactoryBean<T> {

	/**返回bean实例(共享的或者是单例的), 这个bean实例通常由beanFactory来管理,
	 * 如果当getObject()方法被调用时, 这个bean还未完全初始化(例如在循环依赖的时候), 就抛出FactoryBeanNotInitializedException异常,
	 * 从spring2.0开始允许返回null而不抛异常
	 * 官方建议FactoryBean接口的实现类抛异常
	 * Return an instance (possibly shared or independent) of the object
	 * managed by this factory.
	 * <p>As with a {@link BeanFactory}, this allows support for both the
	 * Singleton and Prototype design pattern.
	 * <p>If this FactoryBean is not fully initialized yet at the time of
	 * the call (for example because it is involved in a circular reference),
	 * throw a corresponding {@link FactoryBeanNotInitializedException}.
	 * <p>As of Spring 2.0, FactoryBeans are allowed to return {@code null}
	 * objects. The factory will consider this as normal value to be used; it
	 * will not throw a FactoryBeanNotInitializedException in this case anymore.
	 * FactoryBean implementations are encouraged to throw
	 * FactoryBeanNotInitializedException themselves now, as appropriate.
	 * @return an instance of the bean (can be {@code null})
	 * @throws Exception in case of creation errors
	 * @see FactoryBeanNotInitializedException
	 */
	@Nullable
	T getObject() throws Exception;

	/**返回FactoryBean创建的bean实例的类型, 如果不能提前知道就返回null
	 * 这个方法允许在还未初始化bean实例之前, 检查bean的类型(如自动注入的时候)
	 * 在singleton模式下, 尽量避免用这个方法来创建单例bean
	 * 在prototype模式下, 建议返回有意义的类型
	 * 这个方法可以在factoryBean完成初始化之前被调用, 不管是bean实例正在创建还是bean实例已经创建完成, 都可以调用
	 * 注意 : 自动注入会忽略这里返回null的FactoryBean, 因此强烈建议根据当前FactoryBean的状态适当地实现这个方法
	 * Return the type of object that this FactoryBean creates,
	 * or {@code null} if not known in advance.
	 * <p>This allows one to check for specific types of beans without
	 * instantiating objects, for example on autowiring.
	 * <p>In the case of implementations that are creating a singleton object,
	 * this method should try to avoid singleton creation as far as possible;
	 * it should rather estimate the type in advance.
	 * For prototypes, returning a meaningful type here is advisable too.
	 * <p>This method can be called <i>before</i> this FactoryBean has
	 * been fully initialized. It must not rely on state created during
	 * initialization; of course, it can still use such state if available.
	 * <p><b>NOTE:</b> Autowiring will simply ignore FactoryBeans that return
	 * {@code null} here. Therefore it is highly recommended to implement
	 * this method properly, using the current state of the FactoryBean.
	 * @return the type of object that this FactoryBean creates,
	 * or {@code null} if not known at the time of the call
	 * @see ListableBeanFactory#getBeansOfType
	 */
	@Nullable
	Class<?> getObjectType();

	/**判断这个被beanFactory管理的bean实例是否是单例的, 如果是, 则getObject()方法永远返回同一个对象?
	 * 如果一个FactoryBean持有一个单例的bean, 则getObject()返回的对象有可能是取自它所属的BeanFactory的缓存, 因此不会返回true, 除非这个FactoryBean一直暴露同一个引用变量,
	 * FactoryBean的singleton状态是由所属的BeanFactory提供的,通常被定义成单例
	 * 这个方法返回false不一定代表返回的bean实例是独立的实例, 实现了SmartFactoryBean接口的类可能通过isPrototype()方法显式的定义了这个FactoryBean就是独立的实例
	 * 仅仅实现了FactoryBean接口的类, 在isSingleton()方法返回false时, 只会简单返回独立的实例, 因为FactoryBean默认是返回单例bean
	 * Is the object managed by this factory a singleton? That is,
	 * will {@link #getObject()} always return the same object
	 * (a reference that can be cached)?
	 * <p><b>NOTE:</b> If a FactoryBean indicates to hold a singleton object,
	 * the object returned from {@code getObject()} might get cached
	 * by the owning BeanFactory. Hence, do not return {@code true}
	 * unless the FactoryBean always exposes the same reference.
	 * <p>The singleton status of the FactoryBean itself will generally
	 * be provided by the owning BeanFactory; usually, it has to be
	 * defined as singleton there.
	 * <p><b>NOTE:</b> This method returning {@code false} does not
	 * necessarily indicate that returned objects are independent instances.
	 * An implementation of the extended {@link SmartFactoryBean} interface
	 * may explicitly indicate independent instances through its
	 * {@link SmartFactoryBean#isPrototype()} method. Plain {@link FactoryBean}
	 * implementations which do not implement this extended interface are
	 * simply assumed to always return independent instances if the
	 * {@code isSingleton()} implementation returns {@code false}.
	 * <p>The default implementation returns {@code true}, since a
	 * {@code FactoryBean} typically manages a singleton instance.
	 * @return whether the exposed object is a singleton
	 * @see #getObject()
	 * @see SmartFactoryBean#isPrototype()
	 */
	default boolean isSingleton() {
		return true;
	}

}
