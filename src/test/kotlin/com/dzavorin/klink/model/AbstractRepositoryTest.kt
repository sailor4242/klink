package com.dzavorin.klink.model

import com.dzavorin.klink.KlinkApplication
import com.github.springtestdbunit.DbUnitTestExecutionListener
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests

@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@TestExecutionListeners(DbUnitTestExecutionListener::class)
@SpringBootTest(classes = arrayOf(KlinkApplication::class))
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
abstract class AbstractRepositoryTest : AbstractTransactionalJUnit4SpringContextTests() {
}