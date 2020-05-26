package basilisk.card

case class CardId(value: String) extends AnyVal

case class Card(id: CardId, name: String, attributes: Map[Attr, AttrVal])
