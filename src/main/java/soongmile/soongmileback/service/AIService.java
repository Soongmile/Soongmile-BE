package soongmile.soongmileback.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIService {

    private final ChatgptService chatgptService;

    public String getInfo(String name) {
        return chatgptService.sendMessage(generateMessage(name));
    }

    private String generateMessage(String name) {
        return name + "에 대해 3줄 안으로 설명해줘";
    }
}
