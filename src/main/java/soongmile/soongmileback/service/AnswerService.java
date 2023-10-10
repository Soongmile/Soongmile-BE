package soongmile.soongmileback.service;

import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soongmile.soongmileback.domain.*;
import soongmile.soongmileback.domain.request.AnswerCreateRequest;
import soongmile.soongmileback.domain.response.AnswerCreateResponse;
import soongmile.soongmileback.domain.response.AnswerView;
import soongmile.soongmileback.domain.response.QuestionCreateResponse;
import soongmile.soongmileback.repository.AnswerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Builder
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final AnswerMemberLikeService answerMemberLikeService;
    private final AnswerFileService answerFileService;

    public void createAnswer(AnswerCreateRequest request) {
        // 데이터가 올바르게 전달되었는지 확인
        System.out.println("AnswerCreateRequest content: " + request.getContent());

        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Question question = questionService.findEntityById(request.getQuestionId());

        // answer id X
        Answer answer = Answer.create(request, question, member);

        // answer id o
        Answer save = answerRepository.save(answer);

        question.getAnswers().add(save);

        for (Long fileId : request.getFileIds()) {
            FileEntity file = FileEntity.builder()
                    .id(fileId)
                    .build();

            answerFileService.create(answer, file);
        }

    }

    public AnswerCreateResponse findById(Long id) {
        Answer answer = answerRepository.findById(id).get();

        List<AnswerFile> byAnswer = answerFileService.findByAnswer(answer);
        List<String> urls = byAnswer.stream().map(AnswerFile::getFileEntity).map(FileEntity::getFilePath).collect(Collectors.toList());

        return AnswerCreateResponse
                .builder()
                .content(answer.getContent())
                .memberId(answer.getMember().getId())
                .memberName(answer.getMember().getMemberName())
                .questionId(answer.getQuestion().getId())
                .imageUrls(urls)
                .build();
    }

    @Transactional
    public AnswerCreateResponse likeById(Long id) {
        Answer answer = answerRepository.findById(id).get();
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!answerMemberLikeService.exists(answer.getId(), member.getId())){
            answer.setLikes(answer.getLikes() + 1);
            answerMemberLikeService.create(answer.getId(), member.getId());
        }

        return AnswerCreateResponse
                .builder()
                .content(answer.getContent())
                .memberId(answer.getMember().getId())
                .memberName(answer.getMember().getMemberName())
                .questionId(answer.getQuestion().getId())
                .likes(answer.getLikes())
                .build();
    }

    @Transactional
    public AnswerCreateResponse unlikeById(Long id) {
        Answer answer = answerRepository.findById(id).get();
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!answerMemberLikeService.exists(answer.getId(), member.getId())){
            answer.setLikes(answer.getLikes() - 1);
            answerMemberLikeService.delete(answer.getId(), member.getId());
        }

        return AnswerCreateResponse
                .builder()
                .content(answer.getContent())
                .memberId(answer.getMember().getId())
                .memberName(answer.getMember().getMemberName())
                .questionId(answer.getQuestion().getId())
                .likes(answer.getLikes())
                .build();
    }
}
