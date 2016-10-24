package test.acl.g30x

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.acl.AclUtilService
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.acls.domain.BasePermission
import spock.lang.Specification
import test.acl.g30x.auth.User

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@Integration
@Rollback
class PersonSpec extends Specification {

    @Autowired
    AclUtilService aclUtilService

    def setup() {
    }

    def cleanup() {
    }

    void "test acl"() {
        given:
        User user = new User(username: "admin", password: "admin").save(failOnError: true, flush: true)
        Person person = new Person().save(flush: true, failOnError: true)
        SpringSecurityUtils.doWithAuth("admin") {
            aclUtilService.addPermission(Person, person.id, 'user', BasePermission.READ)
        }

        expect:
        aclUtilService.readAcl(Person, person.id)
    }
}
