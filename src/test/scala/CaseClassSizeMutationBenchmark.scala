import org.scalameter.api._
import org.scalameter.picklers.Implicits._

object CaseClassSizeMutationBenchmark extends Bench[Double] {
  /* configuration */

  lazy val measurer = new Measurer.Default

  lazy val executor = SeparateJvmsExecutor(Warmer.Default(), Aggregator.min[Double], measurer)
  lazy val reporter = ChartReporter[Double](ChartFactory.XYLine())
  lazy val persistor = Persistor.None

  /* inputs */
  
  val classType = Gen.enumeration("class size")(1, 2, 3, 4, 5, 6)

  case class Var01(var a: Int)
  case class Var02(var a: Int, var b: Int)
  case class Var03(var a: Int, var b: Int, var c: Int)
  case class Var04(var a: Int, var b: Int, var c: Int, var d: Int)
  case class Var05(var a: Int, var b: Int, var c: Int, var d: Int, var e: Int)
  case class Var06(var a: Int, var b: Int, var c: Int, var d: Int, var e: Int, var f: Int)

  case class Val01(a: Int)
  case class Val02(a: Int, b: Int)
  case class Val03(a: Int, b: Int, c: Int)
  case class Val04(a: Int, b: Int, c: Int, d: Int)
  case class Val05(a: Int, b: Int, c: Int, d: Int, e: Int)
  case class Val06(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int)

  val var01 = Var01(0)
  val var02 = Var02(0, 0)
  val var03 = Var03(0, 0, 0)
  val var04 = Var04(0, 0, 0, 0)
  val var05 = Var05(0, 0, 0, 0, 0)
  val var06 = Var06(0, 0, 0, 0, 0, 0)

  var val01 = Val01(0)
  var val02 = Val02(0, 0)
  var val03 = Val03(0, 0, 0)
  var val04 = Val04(0, 0, 0, 0)
  var val05 = Val05(0, 0, 0, 0, 0)
  var val06 = Val06(0, 0, 0, 0, 0, 0)

  /* tests */

  performance of "State" in {

    measure method "assign" in {
      using(classType) in {
        case 1 =>
          for( v <- 1 to 1000000) {
            var01.a = v
          }
        case 2 =>
          for( v <- 1 to 1000000) {
            var02.a = v
            var02.b = v
          }
        case 3 =>
          for( v <- 1 to 1000000) {
            var03.a = v
            var03.b = v
            var03.c = v
          }
        case 4 =>
          for( v <- 1 to 1000000) {
            var04.a = v
            var04.b = v
            var04.c = v
            var04.d = v
          }
        case 5 =>
          for( v <- 1 to 1000000) {
            var05.a = v
            var05.b = v
            var05.c = v
            var05.d = v
            var05.e = v
          }
        case 6 =>
          for( v <- 1 to 1000000) {
            var06.a = v
            var06.b = v
            var06.c = v
            var06.d = v
            var06.e = v
            var06.f = v
          }
      }

      using(classType) in {
        case 1 =>
          for( v <- 1 to 1000000) {
            val01 = val01.copy( a = v )
          }
        case 2 =>
          for( v <- 1 to 1000000) {
            val02 = val02.copy( a = v, b = v )
          }
        case 3 =>
          for( v <- 1 to 1000000) {
            val03 = val03.copy( a = v, b = v, c = v )
          }
        case 4 =>
          for( v <- 1 to 1000000) {
            val04 = val04.copy( a = v, b = v, c = v, d = v )
          }
        case 5 =>
          for( v <- 1 to 1000000) {
            val05 = val05.copy( a = v, b = v, c = v, d = v, e = v )
          }
        case 6 =>
          for( v <- 1 to 1000000) {
            val06 = val06.copy( a = v, b = v, c = v, d = v, e = v, f = v )
          }
      }

    }

  }
}

