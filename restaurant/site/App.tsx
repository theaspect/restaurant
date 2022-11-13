import * as React from "react"
import {useEffect, useState} from "react"
import {Col, Row} from "react-bootstrap";

export default function Restaurant() {
    const [restaurants, setRestaurants] = useState([])
    const [active, setActive] = useState(null)

    function load() {
        fetch(RESTAURANT_URL + '/')
            .then((response) => response.json())
            .then((restaurants) => setRestaurants(restaurants))
            .catch((error) => setRestaurants([]));
    }

    function renderTable(restaurants: any[]) {
        return <table>
            {restaurants.map((r) =>
                <tr id={r.id}>
                    <td onClick={() => setActive(r)}>
                        {r["name"]}
                    </td>
                </tr>
            )}
        </table>
    }

    function renderRestaurant(active: any) {
        if (!active) return <>Select a Restaurant</>
        return <>
            <h2>{active.name}</h2>
            <div>{active.city} {active.street} {active.house}</div>
            <div>{active.latitude} {active.longitude}</div>
        </>
    }

    useEffect(() => {
        load()
    }, []);

    return (
        <>
            <h1>Restaurants</h1>
            <Row>
                <Col sm={1}>
                    <div>
                        {renderTable(restaurants)}
                    </div>
                </Col>
                <Col sm={11}>
                    <div>
                        {renderRestaurant(active)}
                    </div>
                </Col>
            </Row>
        </>
    )
}