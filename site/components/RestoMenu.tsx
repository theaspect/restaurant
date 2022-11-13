import * as React from "react"
import {Nav} from "react-bootstrap";
import {LinkContainer} from "react-router-bootstrap";

function RestoMenu() {
    return (
        <Nav className="navbar navbar-light navbar-vertical navbar-expand-xl flex-column text-start" activeKey="/">
            <Nav.Item>
                <LinkContainer to={"/"}>
                    <Nav.Link>
                        <i className="fa-solid fa-utensils me-2"></i>
                        Restaurant
                    </Nav.Link>
                </LinkContainer>
            </Nav.Item>
            <Nav.Item>
                <LinkContainer to={"echo1"}>
                    <Nav.Link>
                        <i className="fa-regular fa-message me-2"></i>
                        Echo 1
                    </Nav.Link>
                </LinkContainer>
            </Nav.Item>
            <Nav.Item>
                <LinkContainer to={"echo2"}>
                    <Nav.Link>
                        <i className="fa-regular fa-message me-2"></i>
                        Echo 2
                    </Nav.Link>
                </LinkContainer>
            </Nav.Item>
        </Nav>
    )
}

export default RestoMenu