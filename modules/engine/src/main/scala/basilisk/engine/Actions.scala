package basilisk
package engine

import cats.data.NonEmptyList

sealed trait Actions

case class Shuffle(zone: Zone)

// TODO: STM monad, need transactions for atomic actions like Mulligan, cascade
// TODO auto free library from 47deg?
trait ZoneAlg[F[_]] {

  def listMembers[P <: ZonePositioning](zone: Zone.Aux[P]): F[Vector[(Card, ZonePosition[P])]]

  def draw[P <: ZonePositioning](
      zone: Zone.Aux[P],
      count: Int
  ): F[(Zone.Aux[P], NonEmptyList[Card])]

  def shuffle(zone: Zone.Ordered): F[Zone.Ordered]

  def place[P <: ZonePositioning](
      zone: Zone.Aux[P],
      card: Card,
      pos: ZonePosition[P]
  ): F[Zone.Aux[P]]
}
