package basilisk

import cats.data.NonEmptyVector

/** An attribute that a card may have: name, rank, suite, mana cost, etc */
trait Attr {
  def id: String
  def attrType: AttrType
}

object Attr {

  def apply[T <: AttrType](id: String, attrType: T): AttrOf[T] =
    AttrOf(id, attrType)
}
case class AttrOf[T <: AttrType](id: String, attrType: T) extends Attr

/** The variant type of an attribute */
sealed trait AttrType {

  /** The scala type contained in this variant */
  type T

  def apply(value: T): AttrVal.Of[T]
}

object AttrType {
  type Aux[A] = AttrType { type T = A }

  case object S extends AttrType {
    type T = String
    def apply(value: String): AttrVal.Of[String] = AttrVal.S(value)
  }

  case object B extends AttrType {
    type T = Boolean
    def apply(value: Boolean): AttrVal.Of[Boolean] = AttrVal.B(value)
  }

  case object N extends AttrType {
    type T = BigDecimal
    def apply(value: BigDecimal): AttrVal.Of[BigDecimal] = AttrVal.N(value)
  }

  case object Ref extends AttrType {
    type T = CardId
    def apply(value: CardId): AttrVal.Of[CardId] = AttrVal.Ref(value)
  }

  case class Lst[A <: AttrType](attrType: A) extends AttrType {
    type T = List[attrType.T]

    def apply(value: List[attrType.T]): AttrVal.Of[List[attrType.T]] =
      new AttrVal.Lst(this.asInstanceOf[AttrType.Aux[List[attrType.T]]])(value)
  }

  case class Enum(validValues: NonEmptyVector[String]) extends AttrType {
    type T = String
    val attrValues = validValues.map(AttrVal.S)
    def apply(value: String): AttrVal.Of[String] = AttrVal.S(value)
  }
}

sealed trait AttrVal {
  val attrType: AttrType
  val value: attrType.T
}

object AttrVal {

  sealed abstract class Of[A](val attrType: AttrType.Aux[A])(val value: A) extends AttrVal

  case class S(override val value: String) extends Of(AttrType.S)(value)
  case class B(override val value: Boolean) extends Of(AttrType.B)(value)
  case class N(override val value: BigDecimal) extends Of(AttrType.N)(value)
  case class Ref(override val value: CardId) extends Of(AttrType.Ref)(value)

  class Lst[A](t: AttrType.Aux[List[A]])(override val value: List[A]) extends Of(t)(value)
}
