// https://stackoverflow.com/a/71686319/7991462
import * as React from "react"
import {StrictMode} from "react"
import {createRoot} from "react-dom/client";

import {createBrowserRouter, RouterProvider,} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.min';
import '@fortawesome/fontawesome-free/css/all.css';
import ErrorPage from "./components/ErrorPage";
import Echo1 from "../echo1/site/App";
import Echo2 from "../echo2/site/App";
import Restaurant from "../restaurant/site/App";
import Layout from "./components/Layout";

const root = createRoot(document.getElementById("app"));

const router = createBrowserRouter([
    {
        path: "/",
        element: <Layout/>,
        errorElement: <ErrorPage/>,
        children: [
            {
                path: "/echo1",
                element: <Echo1/>
            },
            {
                path: "/echo2",
                element: <Echo2/>
            },
            {
                path: "/restaurant",
                element: <Restaurant/>
            }
        ]
    },
]);

root.render(
    <StrictMode>
        <RouterProvider router={router}/>
    </StrictMode>
);
