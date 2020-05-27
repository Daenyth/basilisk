package basilisk

/** An identifier for a specific card, during a game */
case class CardId(value: Int) extends AnyVal

/** A description of a card, as an abstract platonic unit. */
case class CardSchema(uid: String, name: String, cardType: CardType, attributes: Map[Attr, AttrVal])

/** A specific instance of a card, during a game */
case class Card(id: CardId, schema: CardSchema)

case class CardType(name: String, parentTypes: List[CardType])
