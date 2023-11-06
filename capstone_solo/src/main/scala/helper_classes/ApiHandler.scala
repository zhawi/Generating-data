package helper_classes

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.RootJsonFormat

object ApiHandler {

  def apiHandle(usersList: List[FlatUser]): Unit = {
    implicit val system: ActorSystem = ActorSystem("my-system")

    // Passing all the parameters to an implicit with JsonFormat
    implicit val flatUserFormat: RootJsonFormat[FlatUser] = jsonFormat21(FlatUser)

    // Define the API routes needed for the exercise
    val route =
      pathPrefix("users") {
        get {
          parameter(Symbol("id")) { id =>
            complete(usersList.filter(_.id == id.toInt))
          } ~
          parameter(Symbol("tagTwo")) { tag =>
            complete(usersList.filter(_.tags.contains(tag.toLowerCase)))
          } ~
          parameter(Symbol("state")) { state =>
            complete(usersList.filter(_.state.toLowerCase == state.toLowerCase))
          }
        }
      }

    // Binding the route to a port on the localhost
    Http().newServerAt("localhost", 8080).bind(route)

    println(s"Server online at http://localhost:8080/")
  }
}
