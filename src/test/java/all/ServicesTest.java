package all;

import all.configuration.JavaConfiguration;
import all.dao.ClientDao;
import all.entity.Client;
import all.service.ClientService;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JavaConfiguration.class})
@WebAppConfiguration
public class ServicesTest {

    @Mock
    ClientDao clientDaoMock;
    @InjectMocks
    @Autowired
    ClientService clientService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        Mockito.when(clientDaoMock.getClientById(1)).thenReturn(new Client());
        Client clientById = clientService.getClientById(1);
        Assert.notNull(clientById);
    }
}
