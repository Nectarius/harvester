<@forAllTypes var="type" annotationVar="datatypeDefinition" annotation="javax.persistence.Entity">

<#assign proxyPackage = "ru.bn.proxy">
<#assign proxyClassName = "${type.simpleName}Proxy">

<@javaSource name="${proxyPackage}.${proxyClassName}">

package ru.bn.testerbn.workbench.shared.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

<#if type.getSuperclass()= "java.lang.Object">

@ProxyFor(Plain${type.simpleName}View.class)
public interface ${proxyClassName} extends ValueProxy {
<#else>
@ProxyFor(Plain${type.simpleName}View.class)
public interface ${proxyClassName} extends ${type.getSuperclass().getDeclaration().simpleName}Proxy , ValueProxy {

</#if>
       <@forAllFields var="field" excludes="javax.persistence.ManyToOne,javax.persistence.OneToOne,javax.persistence.OneToMany">
			<#if !field.modifiers?seq_contains("static") >
			
			public ${field.type} get${field.simpleName?cap_first}();
			
			public void set${field.simpleName?cap_first}(${field.type} value);
			
			</#if>
			
		</@forAllFields>	

}

</@javaSource>
</@forAllTypes>