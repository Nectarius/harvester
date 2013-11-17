
<@forAllTypes var="type" annotationVar="datatypeDefinition" annotation="javax.persistence.Entity">

<#assign fakeProxyPackage = "ru.bn.fakeProxy">
<#assign fakeProxyClassName = "${type.simpleName}FakeProxy">
<#assign proxyClassName = "${type.simpleName}Proxy">
<#assign proxyPackage = "ru.bn.proxy">

<@javaSource name="${fakeProxyPackage}.${fakeProxyClassName}">

package ${fakeProxyPackage};

import ${proxyPackage}.${proxyClassName};

<#if type.getSuperclass()= "java.lang.Object">

public class ${fakeProxyClassName} implements ${proxyClassName}{

<#else>

public class ${fakeProxyClassName} extends ${type.getSuperclass().getDeclaration().simpleName}FakeProxy implements ${proxyClassName}{

</#if>

<@forAllFields var="field"  excludes="javax.persistence.ManyToOne,javax.persistence.OneToOne,javax.persistence.OneToMany" >
	private ${field.type} ${field.simpleName};
</@forAllFields>

	
<@forAllFields var="field"  excludes="javax.persistence.ManyToOne,javax.persistence.OneToOne,javax.persistence.OneToMany">
    <#if !field.modifiers?seq_contains("static") >
		
		public ${field.type} get${field.simpleName?cap_first}() {
			return ${field.simpleName};
		}		
	
		public void set${field.simpleName?cap_first}(${field.type} ${field.simpleName}) {
			this.${field.simpleName} = ${field.simpleName};
		}
	</#if>
</@forAllFields>



}

</@javaSource>
</@forAllTypes>