import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Link as RouterLink, useNavigate } from 'react-router-dom';
import { registerUser } from '../api/authService';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';
import { useState } from "react";
import { useFormik } from 'formik';
import * as Yup from 'yup';

const validationSchema = Yup.object({
    firstname: Yup.string().required('First name is required'),
    lastname: Yup.string().required('Last name is required'),
    email: Yup.string().email('Enter a valid email').required('Email is required'),
    password: Yup.string().min(8, 'Password should be of minimum 8 characters length').required('Password is required'),
    role: Yup.string().required('Role is required')
});

const defaultTheme = createTheme();

export default function SignUp() {
    const [openSnackbar, setOpenSnackbar] = useState(false);
    const navigate = useNavigate();

    const formik = useFormik({
        initialValues: {
            firstname: '',
            lastname: '',
            email: '',
            password: '',
            role: ''
        },
        validationSchema: validationSchema,
        onSubmit: async (values) => {
            try {
                await registerUser(values);
                console.log('Registration process was successful');
                setOpenSnackbar(true);
                navigate('/');
                formik.resetForm();
            } catch (error) {
                console.error('Error during registration process:', error);
            }
        },
    });

    const handleCloseSnackbar = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        setOpenSnackbar(false);
    };

    return (
        <ThemeProvider theme={defaultTheme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Sign up
                    </Typography>
                    <Box component="form" noValidate onSubmit={formik.handleSubmit} sx={{ mt: 3 }}>
                        <Grid container spacing={2}>
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    autoComplete="given-name"
                                    name="firstname"
                                    required
                                    fullWidth
                                    id="firstname"
                                    label="First Name"
                                    autoFocus
                                    value={formik.values.firstname}
                                    onChange={formik.handleChange}
                                    error={formik.touched.firstname && Boolean(formik.errors.firstname)}
                                    helperText={formik.touched.firstname && formik.errors.firstname}
                                />
                            </Grid>
                            <Grid item xs={12} sm={6}>
                                <TextField
                                    required
                                    fullWidth
                                    id="lastName"
                                    label="Last Name"
                                    name="lastname"
                                    autoComplete="family-name"
                                    value={formik.values.lastname}
                                    onChange={formik.handleChange}
                                    error={formik.touched.lastname && Boolean(formik.errors.lastname)}
                                    helperText={formik.touched.lastname && formik.errors.lastname}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="email"
                                    label="Email Address"
                                    name="email"
                                    autoComplete="email"
                                    value={formik.values.email}
                                    onChange={formik.handleChange}
                                    error={formik.touched.email && Boolean(formik.errors.email)}
                                    helperText={formik.touched.email && formik.errors.email}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    name="password"
                                    label="Password"
                                    type="password"
                                    id="password"
                                    autoComplete="new-password"
                                    value={formik.values.password}
                                    onChange={formik.handleChange}
                                    error={formik.touched.password && Boolean(formik.errors.password)}
                                    helperText={formik.touched.password && formik.errors.password}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <FormControl fullWidth>
                                    <InputLabel id="role-select-label">Role</InputLabel>
                                    <Select
                                        labelId="role-select-label"
                                        id="role"
                                        name="role"
                                        value={formik.values.role}
                                        label="Role"
                                        onChange={formik.handleChange}
                                        error={formik.touched.role && Boolean(formik.errors.role)}
                                    >
                                        <MenuItem value={"USER"}>User</MenuItem>
                                        <MenuItem value={"ADMIN"}>Admin</MenuItem>
                                    </Select>
                                </FormControl>
                            </Grid>
                        </Grid>
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                            Sign Up
                        </Button>
                        <Grid container justifyContent="flex-end">
                            <Grid item>
                                <Link component={RouterLink} to="/" variant="body2">
                                    Already have an account? Sign in
                                </Link>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
                <Snackbar open={openSnackbar} autoHideDuration={6000} onClose={handleCloseSnackbar}>
                    <Alert onClose={handleCloseSnackbar} severity="success" sx={{ width: '100%' }}>
                        Registration successful!
                    </Alert>
                </Snackbar>
            </Container>
        </ThemeProvider>
    );
}
