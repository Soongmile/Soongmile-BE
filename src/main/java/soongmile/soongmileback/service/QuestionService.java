package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soongmile.soongmileback.domain.FileEntity;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.domain.Question;
import soongmile.soongmileback.domain.QuestionFile;
import soongmile.soongmileback.domain.request.QuestionCreateRequest;
import soongmile.soongmileback.domain.response.AnswerView;
import soongmile.soongmileback.domain.response.QuestionCreateResponse;
import soongmile.soongmileback.domain.response.QuestionViewResponse;
import soongmile.soongmileback.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMemberLikeService questionMemberLikeService;
    private final QuestionFileService questionFileService;

    @Transactional
    public List<QuestionViewResponse> getMainPage(int page, int size) {
        List<QuestionViewResponse> ret = new ArrayList<>();
        List<Question> questions = questionRepository.findAll();
        for (int i = (page - 1) * size; i < page * size; i++) {
            if (i >= questions.size())
                break;
            ret.add(new QuestionViewResponse(questions.get(i).getId(), questions.get(i).getTitle(), questions.get(i).getContent(), questions.get(i).getTag(), questions.get(i).getField(), questions.get(i).getPostTime(), questions.get(i).getHits(), questions.get(i).getAnswers().size()));
        }
        return ret;
    }

    @Transactional
    public List<QuestionViewResponse> search(String keyword, int page, int size) {
        List<QuestionViewResponse> ret = new ArrayList<>();
        List<Question> questions = questionRepository.findByTitleContaining(keyword);
        for (int i = (page - 1) * size; i < page * size; i++) {
            if (i >= questions.size())
                break;
            ret.add(new QuestionViewResponse(questions.get(i).getId(), questions.get(i).getTitle(), questions.get(i).getContent(), questions.get(i).getTag(), questions.get(i).getField(), questions.get(i).getPostTime(), questions.get(i).getHits(), questions.get(i).getAnswers().size()));
        }
        return ret;
    }

    @Transactional
    public void createQuestion(QuestionCreateRequest request) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Question question = Question.create(request, member);
        questionRepository.save(question);

        for (Long fileId : request.getFileIds()) {
            FileEntity file = FileEntity.builder()
                    .id(fileId)
                    .build();

            questionFileService.create(question, file);
        }

    }

    @Transactional
    public List<QuestionViewResponse> showQuestions(Member member) {
        List<QuestionViewResponse> ret = new ArrayList<>();
        for (Question question : member.getQuestions()) {
            ret.add(new QuestionViewResponse(question.getId(), question.getTitle(), question.getContent(), question.getTag(), question.getField(), question.getPostTime(), question.getHits(), question.getAnswers().size()));
        }
        return ret;
    }

    @Transactional
    public QuestionCreateResponse findById(Long id) {
        Question question = questionRepository.findById(id).get();
        question.setHits(question.getHits() + 1);

        List<QuestionFile> byQuestion = questionFileService.findByQuestion(question);
        List<String> urls = byQuestion.stream().map(QuestionFile::getFileEntity).map(FileEntity::getFilePath).collect(Collectors.toList());

        List<AnswerView> collect = question.getAnswers().stream().map(AnswerView::create).collect(Collectors.toList());

        return QuestionCreateResponse
                .builder()
                .title(question.getTitle())
                .content(question.getContent())
                .tag(question.getTag())
                .postTime(question.getPostTime())
                .field(question.getField())
                .memberId(question.getMember().getId())
                .memberName(question.getMember().getMemberName())
                .hits(question.getHits())
                .likes(question.getLikes())
                .answerList(collect)
                .imageUrls(urls)
                .build();
    }

    @Transactional
    public QuestionCreateResponse likeById(Long id) {
        Question question = questionRepository.findById(id).get();
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!questionMemberLikeService.exists(question.getId(), member.getId())){
            question.setLikes(question.getLikes() + 1);
            questionMemberLikeService.create(question.getId(), member.getId());
        }

        List<AnswerView> collect = question.getAnswers().stream().map(AnswerView::create).collect(Collectors.toList());
        return QuestionCreateResponse
                .builder()
                .title(question.getTitle())
                .content(question.getContent())
                .tag(question.getTag())
                .postTime(question.getPostTime())
                .field(question.getField())
                .memberId(question.getMember().getId())
                .memberName(question.getMember().getMemberName())
                .hits(question.getHits())
                .likes(question.getLikes())
                .answerList(collect)
                .build();
    }

    public Question findEntityById(Long id) {
        return questionRepository.findById(id).get();
    }
}
