package basilisk

/** An attribute that a card may have: name, rank, suite, mana cost, etc */
case class Attr(id: String, attrType: AttrType)

/** The variant type of an attribute */
sealed trait AttrType {

  /** The scala type contained in this variant */
  type T
}

object AttrType {
  case object S extends AttrType { type T = String }
  case object B extends AttrType { type T = Boolean }
  case object N extends AttrType { type T = BigDecimal }
  case object Ref extends AttrType { type T = CardId }
  case class Lst[A <: AttrType](attrType: A) extends AttrType { type T = List[AttrVal[A]] }
}

sealed abstract class AttrVal[A <: AttrType](val attrType: AttrType) {
  def value: attrType.T
}

object AttrVal {
  case class S(value: String) extends AttrVal(AttrType.S)
  case class B(value: Boolean) extends AttrVal(AttrType.B)
  case class N(value: BigDecimal) extends AttrVal(AttrType.N)

  case class Lst[A <: AttrType](attrType: A, value: List[AttrVal[A]])
      extends AttrVal(AttrType.Lst(attrType))
}
