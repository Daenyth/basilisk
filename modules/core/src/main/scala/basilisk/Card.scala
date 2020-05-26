package basilisk

case class CardId(value: String) extends AnyVal

case class Card(id: CardId, name: String, cardType: CardType, attributes: Map[Attr, AttrVal])

case class CardType(name: String, parentTypes: List[CardType])
