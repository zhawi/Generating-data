// Databricks notebook source
import spark.implicits._
import org.apache.spark.sql.functions._

// COMMAND ----------

val pathToJson = "/FileStore/tables/generated.json"
val rawDf = spark.read.option("multiline","true").json(pathToJson)

rawDf.show()
rawDf.printSchema()

// COMMAND ----------

val flattenedDF = rawDf
  // Explode arrays
  .withColumn("address", explode($"address"))
  .withColumn("employment", explode($"employment"))
  .withColumn("tags", concat_ws(",", $"tags"))
  
  // Flatten nested fields
  .withColumn("street", $"address.street")
  .withColumn("city", $"address.city")
  .withColumn("state", $"address.state")
  .withColumn("zipcode", $"address.zipcode")
  .withColumn("country", $"address.country")
  .withColumn("job_title", $"employment.job_title")
  .withColumn("department", $"employment.department")
  .withColumn("company", $"employment.company")

  // Optionally drop original nested columns
  .drop("address", "employment")

  flattenedDF.show(false)

// COMMAND ----------

flattenedDF.createOrReplaceTempView("users_temp")

// COMMAND ----------

// MAGIC %sql
// MAGIC select id, email, is_active, joined_date from users_temp limit 10;

// COMMAND ----------

// MAGIC %sql
// MAGIC
// MAGIC select count(*) from users_temp;

// COMMAND ----------

// MAGIC %sql
// MAGIC select
// MAGIC   *
// MAGIC from
// MAGIC   users_temp
// MAGIC where
// MAGIC   lower(state) like ("wisconsin")
// MAGIC order by id;

// COMMAND ----------


