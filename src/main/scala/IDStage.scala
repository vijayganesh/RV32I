import chisel3._
import chisel3.util.log2Ceil

/**
 *
 * @param datawidth -> Data Width By default 32
 * @author P.C. Vijay Ganesh
 * @version 0.1
 *@todo Need to work on additional control signal
 *       @todo Need to create Wiki Page
 *       @see see yet to create Wiki page
 */
class IDStage(val datawidth : Int = 32) extends Module{

  val io = IO(new Bundle() {
  val ReadReg1 = Input(UInt(log2Ceil(datawidth).W))
  val ReadReg2 = Input(UInt(log2Ceil(datawidth).W))
  val WriteRegAddress = Input(UInt(log2Ceil(datawidth).W))
  val WriteRegData = Input(UInt(datawidth.W))
  val ReadReg1Data = Output(UInt(datawidth.W))
  val ReadReg2Data = Output(UInt(datawidth.W))
  val memRead = Input(UInt(1.W))
  val memWrite = Input(UInt(1.W))



})
  /**
   * Has the number of register same as that of datawidth
   */
  val X = Mem(datawidth,UInt(datawidth.W))
  X(0) := 0.U // Hard wired one Should not change

  when(io.memRead === 1.U ) {
    io.ReadReg1Data := X(io.ReadReg1)
    io.ReadReg2Data := X(io.ReadReg2)
  }
    .otherwise{
      // Do nothing
    }

  when(io.memWrite === 1.U)
  {
   when(io.WriteRegAddress =/= 0.U ){
     X(io.WriteRegAddress) := io.WriteRegData
   }
     .otherwise{
       // This should be zero always
       X(0) := 0.U
     }

  }
    .otherwise{
      // Do Nothing when Deasserted
    }

}
