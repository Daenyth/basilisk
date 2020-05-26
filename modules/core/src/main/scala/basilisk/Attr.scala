package basilisk

/** An attribute that a card may have: name, rank, suite, mana cost, etc */
case class Attr(id: String, attrType: AttrType)

/** The variant type of an attribute */
sealed trait AttrType {

  /** The scala type contained in this variant */
  type T
}

object AttrType {
  type Aux[A] = AttrType { type T = A }
  case object S extends AttrType { type T = String }
  case object B extends AttrType { type T = Boolean }
  case object N extends AttrType { type T = BigDecimal }
  case object Ref extends AttrType { type T = CardId }
  case class Lst[A <: AttrType](attrType: A) extends AttrType { type T = List[attrType.T] }
}

sealed trait AttrVal {
  val attrType: AttrType
  val value: attrType.T
}

object AttrVal {

  sealed abstract class AttrValOf[A](val attrType: AttrType.Aux[A])(val value: A) extends AttrVal

  case class S(override val value: String) extends AttrValOf(AttrType.S)(value)
  case class B(override val value: Boolean) extends AttrValOf(AttrType.B)(value)
  case class N(override val value: BigDecimal) extends AttrValOf(AttrType.N)(value)

  class Lst[A](t: AttrType.Aux[List[A]])(override val value: List[A]) extends AttrValOf(t)(value)
}
