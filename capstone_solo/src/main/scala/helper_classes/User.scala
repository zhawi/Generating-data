package helper_classes

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
