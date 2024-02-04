import React from 'react';
import useSWR from 'swr';
import {
    CircularProgress,
    Container,
    Alert,
    Typography,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper
} from '@mui/material';
import { getAllUsersData } from '../api/secured/adminPanelService';

const fetcher = () => getAllUsersData();

const UserManagement = () => {
    const { data: users, error } = useSWR('/admin/users', fetcher);

    if (error) {
        return (
            <Container maxWidth="lg" sx={{ mt: 4 }}>
                <Alert severity="error">An error occurred while loading the users. Please try refreshing the page.</Alert>
            </Container>
        );
    }

    if (!users) {
        return (
            <Container maxWidth="lg" sx={{ mt: 4, display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                <CircularProgress />
            </Container>
        );
    }

    return (
        <Container maxWidth="lg" sx={{ mt: 4 }}>
            <Typography variant="h4" sx={{ mb: 4 }}>Application Users</Typography>
            {users.length > 0 ? (
                <TableContainer component={Paper}>
                    <Table aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell>Email</TableCell>
                                <TableCell align="right">First Name</TableCell>
                                <TableCell align="right">Last Name</TableCell>
                                <TableCell align="right">Role</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {users.map((user) => (
                                <TableRow
                                    key={user.id}
                                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                >
                                    <TableCell component="th" scope="row">
                                        {user.email}
                                    </TableCell>
                                    <TableCell align="right">{user.firstname}</TableCell>
                                    <TableCell align="right">{user.lastname}</TableCell>
                                    <TableCell align="right">{user.role}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            ) : (
                <Alert severity="info">No users found.</Alert>
            )}
        </Container>
    );
};

export default UserManagement;
