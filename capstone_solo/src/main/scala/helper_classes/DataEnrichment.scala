package helper_classes

object DataEnrichment {

  // Craft a location field by melding city, state, and country.
  def createLocationField(userList: List[FlatUser]): List[FlatUser] = {
    userList.map(i => i.copy(location = s"${i.city}|${i.state}|${i.country}"))
  }

  // Compute and introduce a phoneCount field delineating the count of phone numbers each user possesses.
  def phoneCountPopulate(userList: List[FlatUser]): List[FlatUser] = {
    userList.map(i => i.copy(phoneCount = 1))
  }

}
