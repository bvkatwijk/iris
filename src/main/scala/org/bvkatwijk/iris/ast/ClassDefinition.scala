package org.bvkatwijk.iris.ast

case class ClassDefinition(qualifiedIdentifier: QualifiedIdentifier) {
  def toJava: String =
    s"""public class ${qualifiedIdentifier.javaClassName} { @Override public String toString() { return "[${qualifiedIdentifier.javaClassName}]"; } }"""
}
