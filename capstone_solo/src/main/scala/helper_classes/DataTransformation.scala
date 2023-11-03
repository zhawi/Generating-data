package helper_classes

object DataTransformation {

  //function to transform the email into lower case.
  def emailLowerCase(userList: List[FlatUser]): List[FlatUser] = {
    userList.map(i => i.copy(email = i.email.toLowerCase))
  }

  //function to populate a nameInitials field with initials of user names.
  def userNameInitials(userList: List[FlatUser]): List[FlatUser] = {
    userList.map(i => i.copy(nameInitials = i.name.split(" ").flatMap(_.headOption).mkString))
  }

  //function to morph the array of tags into a solitary comma-separated string.
  def tagMorph(userList: List[FlatUser]): List[FlatUser] = {
    userList.map(i => i.copy(tagTwo = i.tags.mkString(",")))
  }

}
