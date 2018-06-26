package all;

import all.configuration.JavaConfiguration;
import all.dao.ClientDao;
import all.entity.Client;
import all.entity.Sex;
import all.service.ClientService;
import lombok.experimental.FieldDefaults;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
    }

    //FixMe
    @Test
    public void saveClient() {
        Client client = new Client();
        clientDaoMock.saveClient((client));
        verify(clientDaoMock, times(1)).saveClient(any(Client.class));

    }

    //FixMe
    @Test
    public void deleteClientById() {
        clientDaoMock.deleteClientById(1);
        verify(clientDaoMock, times(1)).deleteClientById(1);
    }

    @Test
    public void getClientById() {
        when(clientDaoMock.getClientById(1)).thenReturn(new Client());
        Client clientById = clientService.getClientById(1);
        Assert.assertNotNull(clientById);
    }

    //FixMe
    @Test
    public void getListClients() {
        List<Client> all = new ArrayList<>();
        all.add(new Client(1, "Rom", "Romanov", Sex.MAN, 1990, 170, 99));
        all.add(new Client(1, "Gor", "Goranov", Sex.MAN, 1991, 177, 88));
        when(clientDaoMock.getListClients()).thenReturn(all);
        List<Client> result = clientDaoMock.getListClients();
        verify(clientDaoMock, times(1)).getListClients();
    }


}
