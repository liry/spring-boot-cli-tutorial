@Controller
@RequestMapping("/")
class SimplestController {

    @RequestMapping
    @ResponseBody
    def helloWorld() { "Hello World" }

}
