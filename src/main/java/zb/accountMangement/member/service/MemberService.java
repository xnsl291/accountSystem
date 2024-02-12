package zb.accountMangement.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zb.accountMangement.common.type.ErrorCode;
import zb.accountMangement.member.domain.Member;
import zb.accountMangement.member.dto.UpdateUserDto;
import zb.accountMangement.common.exception.NotFoundUserException;
import zb.accountMangement.member.repository.MemberRepository;
import zb.accountMangement.member.type.RoleType;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
  private final MemberRepository memberRepository;

  /**
   * 회원 정보 열람
   * @param userId - id
   * @return Member
   */
  public Member getUserInfo(long userId) {
    return memberRepository.findById(userId).orElseThrow(
        () -> new NotFoundUserException(ErrorCode.USER_NOT_EXIST));
  }

  /**
   * 회원 정보 수정
   * @param userId - id
   * @param updateUserDto
   * @return Member
   */
  @Transactional
  public Member updateUserInfo(long userId, UpdateUserDto updateUserDto) {
    Member member = memberRepository.findById(userId).orElseThrow(
        () -> new NotFoundUserException(ErrorCode.USER_NOT_EXIST));

      member.setName(updateUserDto.getName());
      member.setPassword(updateUserDto.getPassword());
      member.setPhoneNumber(updateUserDto.getPhoneNumber());
      memberRepository.save(member);
      return member;
  }

  /**
   * 회원탈퇴
   * @param userId - id
   */
  @Transactional
  public String deleteUser(long userId){
    Member member = memberRepository.findById(userId).orElseThrow(
        () -> new NotFoundUserException(ErrorCode.USER_NOT_EXIST));

      member.setRole(RoleType.WITHDRAWN);
      member.setDeletedAt(LocalDateTime.now());
      return "회원탈퇴완료";
    }
}
