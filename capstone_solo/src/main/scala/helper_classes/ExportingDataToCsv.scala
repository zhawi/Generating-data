package helper_classes

import java.io.{FileWriter, BufferedWriter}
import au.com.bytecode.opencsv.CSVWriter

object ExportingDataToCsv {

  def exportingDataToCsv(userList: List[FlatUser], folderPath: String): Unit = {

    val outputFile = new BufferedWriter(new FileWriter(folderPath))
    val csvWriter = new CSVWriter(outputFile)

    try {
      // Header for CSV file
      val csvSchema = Array(
        "id",
        "name",
        "email",
        "dob",
        "is_active",
        "joined_date",
        "street",
        "city",
        "state",
        "zipcode",
        "country",
        "phone",
        "website",
        "tags",
        "job_title",
        "department",
        "company",
        "location",
        "phoneCount",
        "nameInitials")

      // Write header to CSV
      csvWriter.writeNext(csvSchema)

      // Convert each FlatUser to an Array and write it to the CSV
      userList.foreach { user =>
        val userRow = Array(
          user.id.toString,
          user.name,
          user.email,
          user.dob,
          user.is_active.toString,
          user.joined_date,
          user.street,
          user.city,
          user.state,
          user.zipcode.toString,
          user.country,
          user.phone,
          user.website,
          user.tags.mkString(","),
          user.job_title,
          user.department,
          user.company,
          user.location,
          user.phoneCount,
          user.nameInitials
        )

        //transforming userRow into a Array of Strings
        val stringRow: Array[String] = userRow.map(_.toString)

        // Writting the user data to CSV
        csvWriter.writeNext(stringRow)
      }
    } finally {
      // Close the writer regardless of whether the write succeeded
      csvWriter.close()
      outputFile.close()
    }

    println("CSV file created successfully.")


  }
}
