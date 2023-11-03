package helper_classes

object DataFiltering {

  //1- Filtering
  // Cull records for users who joined after a particular date
  def filterJoinDate(userList: List[User], joinedDate: String): List[User] = {
    userList.filter(_.joined_date < joinedDate)
  }

  // Isolate users based on specific tags, e.g., "developer"
  def isolatedUsersByTag(userList: List[User], tag: String): List[User] = {
    userList.filter(_.tags.contains(tag))
  }

  // Pick out users based on their active status.
  def isActive(userList: List[User]): List[User] = {
    userList.filter(_.is_active)
  }

  // Spotlight users boasting more than one phone number.
  def spotlightUsers(userList: List[FlatUser]): Unit = {
    userList.filter(_.phoneCount >= 1).foreach(i => println(f"${i.name} has more than one phone numbers!"))
  }
}
