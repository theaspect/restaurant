import * as React from "react"

import 'bootstrap/dist/css/bootstrap.min.css';
import '../css/custom.css'
import {Col, Container, Row} from "react-bootstrap";
import RestoMenu from "./RestoMenu";
import {Outlet} from "react-router-dom";

function Layout() {
    return (
        <>
            <main id={"top"}>
                <Container fluid>
                    <Row>
                        <Col sm={1}>
                            <RestoMenu/>
                        </Col>
                        <Col sm={11} className={"content"}>
                            <Outlet/>
                        </Col>
                    </Row>
                </Container>
            </main>
        </>
    )
}

export default Layout