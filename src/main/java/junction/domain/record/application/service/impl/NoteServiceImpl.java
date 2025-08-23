package junction.domain.record.application.service.impl;

import jakarta.transaction.Transactional;
import junction.domain.record.application.service.NoteService;
import junction.domain.record.domain.repository.NoteRepository;
import junction.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NoteServiceImpl implements NoteService {

    private final UserRepository userRepository;
    private final NoteRepository recordRepository;
}
