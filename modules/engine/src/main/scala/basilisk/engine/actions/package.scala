package basilisk.engine

import basilisk.ZoneId
import io.github.timwspence.cats.stm.STM
import cats.implicits._
import scala.util.Random
import monocle.Traversal
import monocle.function.all._
import cats.Applicative

package object actions {

  def shuffle(zoneId: ZoneId) = Action { game =>
    for {
      g <- game.get
      _ <- Applicative[STM].whenA(zoneId.value > g.zones.length)(
             STM.abort(new IllegalArgumentException(s"No Zone with id $zoneId found")))

      newState = (Lenses.gameZone composeOptional index(zoneId.value) composeLens Lenses.zoneCards)
                   .modify(Random.shuffle(_))
      _ <- game.modify(newState)
    } yield ()
  }
}
