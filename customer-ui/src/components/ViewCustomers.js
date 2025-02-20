import React, { useEffect, useState } from 'react';
import { 
  Paper, 
  Table, 
  TableBody, 
  TableCell, 
  TableContainer, 
  TableHead, 
  TableRow,
  Typography,
  Alert,
  Box
} from '@mui/material';
import customerService from '../services/customerService';

const ViewCustomers = () => {
  const [customers, setCustomers] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    console.log('i fire once');
    fetchCustomers();
  }, []);

  const fetchCustomers = async () => {
    try {
      const response = await customerService.getAllCustomers();
      setCustomers(response?.data || []);
    } catch (error) {
      setError(error.message || 'Failed to fetch customers');
    }
  };

  return (
    <Paper sx={{ p: 3, mt: 4 }}>
      <Typography variant="h5" gutterBottom>Customer List</Typography>
      {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}
      <TableContainer>
        <Table>
          <TableHead>
            <TableRow>
            <TableCell>Id</TableCell>
              <TableCell>First Name</TableCell>
              <TableCell>Last Name</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Date of Birth</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {customers.length > 0 ? (
              customers.map((customer) => (
                <TableRow key={customer.id}>
                   <TableCell>{customer.id}</TableCell>
                  <TableCell>{customer.firstName}</TableCell>
                  <TableCell>{customer.lastName}</TableCell>
                  <TableCell>{customer.email}</TableCell>
                  <TableCell>{customer.dateOfBirth}</TableCell>
                </TableRow>
              ))
            ) : (
              <TableRow>
                <TableCell colSpan={6} align="center" sx={{ py: 3 }}>
                  <Box sx={{ textAlign: 'center' }}>
                    <Typography variant="body1" color="text.secondary">
                      No customers found
                    </Typography>
                  </Box>
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </TableContainer>
    </Paper>
  );
};

export default ViewCustomers;
