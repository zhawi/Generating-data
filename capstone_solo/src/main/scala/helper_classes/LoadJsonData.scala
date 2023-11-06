package helper_classes

import net.liftweb.json.{DefaultFormats, parse}

import scala.io.Source.fromFile

object LoadJsonData {

  implicit val formats = DefaultFormats

  def loadJsonData(pathForFile: String): List[User] = {

    //create a string of the data inside the json
    val fileString: String = fromFile(pathForFile).mkString

    //transform the json string in a json file using liftJson library
    val jsonString = parse(fileString)

    //using liftJson to extract all the data of the json object to a case class object
    jsonString.extract[List[User]]
  }
}
