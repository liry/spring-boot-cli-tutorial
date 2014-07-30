@Grab(group = 'org.codehaus.jackson', module = 'jackson-mapper-asl', version = '1.9.13')

@Controller
@RequestMapping("/chocolates")
class ChocolateController {

    def chocolates = [
            [id: 1, name: "Lindt", percentage: "70%"],
            [id: 2, name: "Liedl", percentage: "15%"]
    ]

    @RequestMapping
    @ResponseBody
    def getAll() {
        chocolates
    }

    @RequestMapping("/{id}")
    @ResponseBody
    def get(@PathVariable long id) {
        def chocolate = chocolates.find { it.id == id }
        if (chocolate == null) {
            throw new ResourceNotFoundException(resourceId: id)
        }
        chocolate
    }

    @ExceptionHandler(ResourceNotFoundException)
    @ResponseBody
    def onResourceNotFound(def exception) {
        [code: 404, id: exception.resourceId]
    }
}

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException extends RuntimeException {
    def resourceId
}
