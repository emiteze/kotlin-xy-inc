package backend.rest.service

import backend.rest.model.PointOfInterest
import backend.rest.repository.PointOfInterestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.math.sqrt

@Service
class PointOfInterestService{

    @Autowired private
    lateinit var repository : PointOfInterestRepository

    fun getPoints(): List<PointOfInterest>{
        return repository.findAll()
    }

    fun getPointsNearby(userLocation: PointOfInterest, maxDistance: Double): List<PointOfInterest>{
        return this.getPoints().filter { point: PointOfInterest ->
            distanceBetweenPoints(point, userLocation) <= maxDistance
        }
    }

    fun findPointById(id: UUID): PointOfInterest{
        return repository.findOne(id)
    }

    fun findPointsByName(name: String): List<PointOfInterest>{
        return repository.findByName(name)
    }

    fun deleteByName(name: String){
        repository.delete(findPointsByName(name))
    }

    fun deleteObject(pointOfInterest: PointOfInterest){
        repository.delete(pointOfInterest)
    }

    fun deleteById(id: UUID){
        repository.delete(id)
    }

    fun savePoint(pointOfInterest: PointOfInterest){
        repository.save(pointOfInterest)
    }

    fun savePoints(pointsOfInterest: List<PointOfInterest>){
        repository.save(pointsOfInterest)
    }

    fun distanceBetweenPoints(originPoint: PointOfInterest, destinationPoint: PointOfInterest): Double{
        return sqrt(
                (Math.pow((originPoint.coordx!! - destinationPoint.coordx!!),2.0)) +
                        (Math.pow((originPoint.coordy!! - destinationPoint.coordy!!),2.0))
        )
    }

}