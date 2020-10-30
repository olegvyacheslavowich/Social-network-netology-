package exceptions

class ReasonNotFoundException(
    reason: Int
) : RuntimeException(
    "Reason with id $reason not found"
) {
}