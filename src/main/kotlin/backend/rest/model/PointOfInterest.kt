package backend.rest.model

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.validation.constraints.Min

@Document(collection = "pointsofinterest")
data class PointOfInterest(
        @NotEmpty(message = "Point name must be informed")
        var name: String? = "",
        @Min(value = 0, message = "X coordinate must not be negative")
        var coordx: Double? = null,
        @Min(value = 0, message = "Y coordinate must not be negative")
        var coordy: Double? = null,
        @Id
        var id : UUID = UUID.randomUUID())