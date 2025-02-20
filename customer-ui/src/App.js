import React from 'react';
import { Container, Box, Tab, Tabs } from '@mui/material';
import CreateCustomer from './components/CreateCustomer';
import ViewCustomers from './components/ViewCustomers';

function App() {
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <Container>
      <Box sx={{ width: '100%', bgcolor: 'background.paper', mt: 3 }}>
        <Tabs value={value} onChange={handleChange} centered>
          <Tab label="Create Customer" />
          <Tab label="View Customers" />
        </Tabs>
      </Box>
      {value === 0 && <CreateCustomer />}
      {value === 1 && <ViewCustomers />}
    </Container>
  );
}

export default App;