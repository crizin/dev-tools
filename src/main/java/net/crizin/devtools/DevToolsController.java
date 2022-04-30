package net.crizin.devtools;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import net.crizin.devtools.processor.ProcessorService;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DevToolsController {

	private final ProcessorService processorService;

	public DevToolsController(ProcessorService processorService) {
		this.processorService = processorService;
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("titles", processorService.getTitles());

		return "index";
	}

	@ResponseBody
	@GetMapping("/preset/timestamp")
	public int presetTimestamp() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	@ResponseBody
	@GetMapping("/preset/random")
	public String presetRandom() throws NoSuchAlgorithmException {
		byte[] bytes = new byte[128];
		SecureRandom.getInstanceStrong().nextBytes(bytes);
		return Hex.encodeHexString(DigestUtils.sha512_256(bytes));
	}

	@PostMapping("/convert")
	public String convert(Model model, @RequestParam String text) {
		model.addAttribute("results", processorService.process(text));

		return "convert";
	}

	@ResponseBody
	@GetMapping(value = "/ip", produces = "text/plain")
	public String ip(HttpServletRequest request) {
		// print all headers
		for (Iterator<String> it = request.getHeaderNames().asIterator(); it.hasNext(); ) {
			String header = it.next();
			for (Iterator<String> it2 = request.getHeaders(header).asIterator(); it2.hasNext(); ) {
				System.out.println(header + ": " + it2.next());
			}
		}
		return request.getRemoteAddr();
	}
}