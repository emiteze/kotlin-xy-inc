package backend.rest.controller

import backend.rest.model.PointOfInterest
import backend.rest.service.PointOfInterestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class PointOfInterestController {

    @Autowired private
    lateinit var service: PointOfInterestService

    @RequestMapping(value = ["/get-point"], method = [RequestMethod.GET])
    fun findPoint(
            @RequestParam(value = "id", required = false)
            id: UUID?,
            @RequestParam(value = "name", required = false)
            name: String?): ResponseEntity<Any> =
        when {
            id != null -> {
                service.findPointById(id)
                ResponseEntity.ok(service.findPointById(id))
            }

            !name.isNullOrEmpty() -> {
                service.findPointsByName(name!!)
                ResponseEntity.ok(service.findPointsByName(name))
            }

            else -> ResponseEntity.badRequest().build()
        }


    @RequestMapping(value = ["/get-points"], method = [RequestMethod.GET])
    fun findPoints(): ResponseEntity<Any> = ResponseEntity.ok(service.getPoints())

    @RequestMapping(value = ["/get-points-nearby"], method = [RequestMethod.GET])
    fun getPointsNearby(
            @RequestParam("coordx", required = true)
            coordx: Double,
            @RequestParam("coordy", required = true)
            coordy: Double,
            @RequestParam("maxDistance", required = true)
            maxDistance: Double): ResponseEntity<Any> =
        ResponseEntity.ok(service.getPointsNearby(PointOfInterest("User Location", coordx, coordy), maxDistance))

    @RequestMapping(value = ["/save-point"], method = [RequestMethod.POST, RequestMethod.PUT])
    fun savePoint(@RequestBody pointOfInterest: PointOfInterest): ResponseEntity<Any> {
        try {
            service.savePoint(pointOfInterest)
            return ResponseEntity.ok(service.getPoints())
        } catch (e: Exception) {
            return ResponseEntity.badRequest().build()
        }
    }

    @RequestMapping(value = ["/save-points"], method = [RequestMethod.POST, RequestMethod.PUT])
    fun savePoints(@RequestBody pointsOfInterest: List<PointOfInterest>): ResponseEntity<Any> {
        try {
            service.savePoints(pointsOfInterest)
            return ResponseEntity.ok(service.getPoints())
        } catch (e: Exception) {
            return ResponseEntity.badRequest().build()
        }
    }

    @RequestMapping(value = ["/delete-point"], method = [RequestMethod.DELETE, RequestMethod.POST])
    fun deletePoint(
            @RequestParam(value = "id", required = false)
            id: UUID?,
            @RequestParam(value = "name", required = false)
            name: String?,
            @RequestBody(required = false)
            pointOfInterest: PointOfInterest?): ResponseEntity<Any> =
        when {
            id != null -> {
                service.deleteById(id)
                ResponseEntity.ok(service.getPoints())
            }

            !name.isNullOrEmpty() -> {
                service.deleteByName(name!!)
                ResponseEntity.ok(service.getPoints())
            }

            pointOfInterest != null -> {
                service.deleteObject(pointOfInterest)
                ResponseEntity.ok(service.getPoints())
            }

            else -> ResponseEntity.badRequest().build()
        }

}