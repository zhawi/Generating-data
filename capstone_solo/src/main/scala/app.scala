import net.liftweb.json._

import scala.io.Source.fromFile

object app {

  implicit val formats = DefaultFormats

  case class Address(street: String, city: String, state: String, zipcode: Int, country: String)

  case class Employment(job_title: String, department: String, company: String)

  case class User(id: Int,
                  name: String,
                  email: String,
                  dob: String,
                  is_active: Boolean,
                  joined_date: String,
                  address: Address,
                  phone: String,
                  website: String,
                  tags: List[String],
                  employment: Employment)

  def main(args: Array[String]): Unit = {

    //Creating a variable called filePath with the path for the json file that we need
    val filePath: String = "src/resources/data.json"

    //create a string of the data inside the json
    val fileString: String = fromFile(filePath).mkString

    //transform the json string in a json file using liftJson library
    val jsonString = parse(fileString)

    //using liftJson to extract all the data of the json object to a case class object
    val m = jsonString.extract[List[User]]

    //printing the specific address of a user from the json list
    m.foreach(println)
  }
}
