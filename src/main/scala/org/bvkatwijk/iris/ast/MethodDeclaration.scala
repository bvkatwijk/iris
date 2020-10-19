package org.bvkatwijk.iris.ast

case class MethodDeclaration(name: String,
                             parameters: Seq[Parameter],
                             returnType: QualifiedIdentifier)
