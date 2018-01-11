package com.brickx.marsrover
import scala.io.Source


class MarsRover(xc: Int, yc: Int, dir: Char, b_x: Int, b_y: Int) {
  var x: Int = xc
  var y: Int = yc
  var direction: Char = dir
  var bnd_x: Int = b_x
  var bnd_y: Int = b_y
  
  var dir_dict = Map(
                     'N' -> Map('L' -> 'W', 'R' -> 'E'),
                     'S' -> Map('L' -> 'E', 'R' -> 'W'),
                     'E' -> Map('L' -> 'N', 'R' -> 'S'),
                     'W' -> Map('L' -> 'S', 'R' -> 'N')
                     )
  var move_dict = Map('N' -> _move_north _,
                      'S' -> _move_south _,
                      'E' -> _move_east _,
                      'W' -> _move_west _
                          )
                     
  def change_direction(step: Char) {
    direction = dir_dict(direction)(step)
    
  }
  
  def _move_east() {
    if (x+1 <= bnd_x)
        x = x+1
//    else
//        raise InvalidCoordinatesException(x+1, y)
  }
  
  def _move_west() {
      if (x-1 >= 0)
          x = x-1
//      else:
//          raise InvalidCoordinatesException(x-1, y)
  }
  
  def _move_north() {
      if (y+1 <= bnd_y)
          y = y+1
//      else:
//          raise InvalidCoordinatesException(x, y+1)
  }
  
  def _move_south() {
      if (y-1 >=0)
          y = y-1
//      else:
//          raise InvalidCoordinatesException(x, y-1)
  }
  
  def move(steps: String) {
    var stp: Char = 'M'
    for ( stp <- steps.toList) {
        if (stp == 'M')
            move_dict.get(direction).get()
        else if (stp == 'L' || stp == 'R')
            change_direction(stp)
//        else:
//            raise InvalidInstructiosException(step)
    }
  }
  
  
  
}


object RoverMover {
  def main(args: Array[String]) {
    
    val path = getClass.getResource("test_data.txt").getPath()
    val test_data = Source.fromFile(path)
    val lines = test_data.getLines()
    val n_r = lines.next().split(" ")
    val bnd_x = n_r.apply(0)
    val bnd_y = n_r.apply(1)
    
    while (lines.hasNext) {
      val n_r = lines.next().split(" ")
      val steps = lines.next()
      val r1 = new MarsRover(n_r.apply(0).toInt, n_r.apply(1).toInt, n_r.apply(2).toCharArray().apply(0), bnd_x.toInt, bnd_y.toInt)
      r1.move(steps)
      println(f"${r1.x} ${r1.y} ${r1.direction}")
    }
    
  }

}


