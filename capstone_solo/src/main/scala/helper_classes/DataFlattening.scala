package helper_classes

object DataFlattening {

  // Function to De-nest the address and employment structures to a flat structure.
  def dataFlattening(userList: List[User]): List[FlatUser] = {
    userList.map(user =>
      FlatUser(
        id = user.id,
        name = user.name,
        email = user.email,
        dob = user.dob,
        is_active = user.is_active,
        joined_date = user.joined_date,
        street = user.address.street,
        city = user.address.city,
        state = user.address.state,
        zipcode = user.address.zipcode,
        country = user.address.country,
        phone = user.phone,
        website = user.website,
        tags = user.tags,
        job_title = user.employment.job_title,
        department = user.employment.department,
        company = user.employment.company
      )
    )
  }

}
