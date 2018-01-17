package backend.rest.repository

import backend.rest.model.PointOfInterest
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface PointOfInterestRepository : MongoRepository<PointOfInterest, UUID> {

    fun findByName(name : String): List<PointOfInterest>

}