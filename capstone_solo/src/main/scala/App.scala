import helper_classes._

object App {

  def main(args: Array[String]): Unit = {

    /* Load Data from Json file ------------------------------------------------

    Creating a variable called filePath with the path for the json file that we need */
    val filePath: String = "src/resources/generated.json"
    val csvPath: String = "src/resources/transformed_data.csv"

    //calling an defined object to extract all the data of the json object to a case class object
    val usersList = LoadJsonData.loadJsonData(filePath)

    /* i. Filtering -------------------------------------------------------------

    1. object and printing records for users who joined after a particular date */
    val dateToFilter = "2019-00-00"
    val filteredUsersPerJoinedData: List[User] = DataFiltering.filterJoinDate(usersList, dateToFilter)

    //Printing the count of values on the filtered data by a specific date
    println(f"The amount of records filtered by joined date ${filteredUsersPerJoinedData.length}\n")

    // 2. Object and printing the amount of records for users with specific tag.
    val tag = "amet"
    val filteredUsersByTag: List[User] = DataFiltering.isolatedUsersByTag(usersList, tag)

    //Printing the count of values on the filtered data by a specific tag
    println(f"The amount of records filtered by a specific tag is ${filteredUsersByTag.length}\n")

    // 3. users based on their active status.
    val filteredByIsActive: List[User] = DataFiltering.isActive(usersList)

    //Printing the count of values on the filtered data by a user being active
    println(f"The amount of records filtered by a specific tag is ${filteredByIsActive.length}\n")


    // ii. Data Flattening -----------------------------------------------------

    // De-nest the address and employment structures to a flat structure.
    val flatUsers = DataFlattening.dataFlattening(usersList)

    // iii. Data Enrichment -----------------------------------------------------

    // 1. Craft a location field by melding city, state, and country.
    val flatUsersWithLocation = DataEnrichment.createLocationField(flatUsers)

    // 2. Compute and introduce a phoneCount field delineating the count of phone numbers each user possesses.
    val flatUsersWithPhoneCount = DataEnrichment.phoneCountPopulate(flatUsersWithLocation)

    // iv. Textual Alterations

    // 1. Transform email addresses into lowercase.
    val flatUserWithEmailLowerCase = DataTransformation.emailLowerCase(flatUsersWithPhoneCount)

    // 2. Craft a nameInitials field, representing the initials of user names.
    val flatUsersWithInitials = DataTransformation.userNameInitials(flatUserWithEmailLowerCase)

    // v. Array-based Tasks

    // Spotlight users boasting more than one phone number.
    DataFiltering.spotlightUsers(flatUsersWithInitials)

    // Morph the array of tags into a solitary comma-separated string.
    val flatUsersWithTagsAsString = DataTransformation.tagMorph(flatUsersWithInitials)

    // Output
    ExportingDataToCsv.exportingDataToCsv(flatUsersWithTagsAsString, csvPath)
  }
}
