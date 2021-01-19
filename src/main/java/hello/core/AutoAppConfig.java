package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//basePackages, basePackageClasses 지정 안하면 AutoAppConfig 하위 패키지 모두 검색
@ComponentScan(
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class, //지정클래스 패키지 아래로 조회
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // 수동 빈 등록이 자동빈등록보다 우선시 된다.
/*    @Bean(name="memoryMemberRepository")
    MemberRepository memoryMemberRepository(){
        return new MemoryMemberRepository();
    }*/
}
