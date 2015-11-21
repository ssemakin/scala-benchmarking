import org.scalameter.api._
import org.scalameter.picklers.Implicits._

object CaseClassDepthMutationBenchmark extends Bench[Double] {
  /* configuration */

  lazy val measurer = new Measurer.Default

  lazy val executor = SeparateJvmsExecutor(Warmer.Default(), Aggregator.min[Double], measurer)
  lazy val reporter = ChartReporter[Double](ChartFactory.XYLine())
  lazy val persistor = Persistor.None

  /* inputs */

  val classType = Gen.enumeration("class size")(1, 2, 3, 4, 5, 6)

  case class Var01(var a: Int)
  case class Var02(var a: Var01)
  case class Var03(var a: Var02)
  case class Var04(var a: Var03)
  case class Var05(var a: Var04)
  case class Var06(var a: Var05)

  case class Val01(a: Int)
  case class Val02(a: Val01)
  case class Val03(a: Val02)
  case class Val04(a: Val03)
  case class Val05(a: Val04)
  case class Val06(a: Val05)

  val var01 = Var01(0)
  val var02 = Var02(Var01(0))
  val var03 = Var03(Var02(Var01(0)))
  val var04 = Var04(Var03(Var02(Var01(0))))
  val var05 = Var05(Var04(Var03(Var02(Var01(0)))))
  val var06 = Var06(Var05(Var04(Var03(Var02(Var01(0))))))

  var val01 = Val01(0)
  var val02 = Val02(Val01(0))
  var val03 = Val03(Val02(Val01(0)))
  var val04 = Val04(Val03(Val02(Val01(0))))
  var val05 = Val05(Val04(Val03(Val02(Val01(0)))))
  var val06 = Val06(Val05(Val04(Val03(Val02(Val01(0))))))

  /* tests */

  performance of "nested-case-class-member" in {

    measure method "assign" in {
      using(classType) in {
        case 1 =>
          for( v <- 1 to 1000000) {
            var01.a = v
          }
        case 2 =>
          for( v <- 1 to 1000000) {
            var02.a.a = v
          }
        case 3 =>
          for( v <- 1 to 1000000) {
            var03.a.a.a = v
          }
        case 4 =>
          for( v <- 1 to 1000000) {
            var04.a.a.a.a = v
          }
        case 5 =>
          for( v <- 1 to 1000000) {
            var05.a.a.a.a.a = v
          }
        case 6 =>
          for( v <- 1 to 1000000) {
            var06.a.a.a.a.a.a = v
          }
      }

      using(classType) in {
        case 1 =>
          for( v <- 1 to 1000000) {
            val01 = val01.copy( a = v )
          }
        case 2 =>
          for( v <- 1 to 1000000) {
            val02 = val02.copy(a = val02.a.copy(a= v))
          }
        case 3 =>
          for( v <- 1 to 1000000) {
            val03 = val03.copy(a = val03.a.copy(a = val03.a.a.copy( a = v )))
          }
        case 4 =>
          for( v <- 1 to 1000000) {
            val04 = val04.copy(a = val04.a.copy(a = val04.a.a.copy(a = val04.a.a.a.copy( a = v ))))
          }
        case 5 =>
          for( v <- 1 to 1000000) {
            val05 = val05.copy(a = val05.a.copy(a = val05.a.a.copy(a = val05.a.a.a.copy(a = val05.a.a.a.a.copy(a = v)))))
          }
        case 6 =>
          for( v <- 1 to 1000000) {
            val06 = val06.copy(a = val06.a.copy(a = val06.a.a.copy(a = val06.a.a.a.copy(a = val06.a.a.a.a.copy(a = val06.a.a.a.a.a.copy(a = v))))))
          }
      }

    }

  }
}

